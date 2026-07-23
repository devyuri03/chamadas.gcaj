/* ============================================
   Chamada — Grande Coral
   Consome a API do backend Spring Boot
   ============================================ */

const API = 'http://localhost:8080';

// Estado da tela
let congregacoes = [];   // [{ id, nome, adolescentes, jovens }]
let maestros = [];       // [{ id, nome, presente }]

const $ = id => document.getElementById(id);


/* ============================================
   Carregamento inicial
   ============================================ */

async function carregar() {
  try {
    const [rc, rm] = await Promise.all([
      fetch(API + '/congregacoes'),
      fetch(API + '/maestros')
    ]);

    if (!rc.ok || !rm.ok) throw new Error('Falha ao carregar dados.');

    congregacoes = (await rc.json()).map(c => ({ ...c, adolescentes: 0, jovens: 0 }));
    maestros = (await rm.json()).map(m => ({ ...m, presente: false }));

    renderCongregacoes();
    renderMaestros();
    atualizarTotais();

  } catch (e) {
    $('lista-congregacoes').innerHTML = '';
    aviso('erro', 'Não foi possível carregar as congregações. Verifique se o servidor está no ar e recarregue a página.');
  }
}


/* ============================================
   Renderização
   ============================================ */

function renderCongregacoes() {
  $('lista-congregacoes').innerHTML = congregacoes.map((c, i) => `
    <div class="card">
      <div class="nome-congregacao">${c.nome}</div>
      ${linhaContador(i, 'adolescentes', 'Adolescentes')}
      ${linhaContador(i, 'jovens', 'Jovens')}
      <div class="subtotal">Total: <b id="sub-${i}">0</b></div>
    </div>
  `).join('');
}

function linhaContador(i, campo, rotulo) {
  return `
    <div class="linha">
      <span>${rotulo}</span>
      <div class="stepper">
        <button class="btn menos" data-i="${i}" data-c="${campo}" data-d="-1"
                aria-label="Diminuir ${rotulo}" disabled>−</button>
        <div class="valor zero" id="v-${i}-${campo}">0</div>
        <button class="btn mais" data-i="${i}" data-c="${campo}" data-d="1"
                aria-label="Aumentar ${rotulo}">+</button>
      </div>
    </div>`;
}

function renderMaestros() {
  $('lista-maestros').innerHTML = maestros.map((m, i) => `
    <div class="maestro" data-m="${i}" role="checkbox" aria-checked="false" tabindex="0">
      <span>${m.nome}</span>
      <div class="check">
        <svg viewBox="0 0 24 24"><polyline points="20 6 9 17 4 12"/></svg>
      </div>
    </div>
  `).join('');
}


/* ============================================
   Interação
   ============================================ */

document.addEventListener('click', e => {
  const btn = e.target.closest('.btn');
  if (btn) return alterar(+btn.dataset.i, btn.dataset.c, +btn.dataset.d);

  const mae = e.target.closest('.maestro');
  if (mae) return alternarMaestro(+mae.dataset.m);
});

document.addEventListener('keydown', e => {
  const mae = e.target.closest('.maestro');
  if (mae && (e.key === ' ' || e.key === 'Enter')) {
    e.preventDefault();
    alternarMaestro(+mae.dataset.m);
  }
});

function alterar(i, campo, delta) {
  const novo = congregacoes[i][campo] + delta;
  if (novo < 0) return;

  congregacoes[i][campo] = novo;

  const el = $(`v-${i}-${campo}`);
  el.textContent = novo;
  el.classList.toggle('zero', novo === 0);

  document.querySelector(`.menos[data-i="${i}"][data-c="${campo}"]`).disabled = novo === 0;
  $(`sub-${i}`).textContent = congregacoes[i].adolescentes + congregacoes[i].jovens;

  atualizarTotais();
}

function alternarMaestro(i) {
  maestros[i].presente = !maestros[i].presente;

  const el = document.querySelector(`.maestro[data-m="${i}"]`);
  el.classList.toggle('ativo', maestros[i].presente);
  el.setAttribute('aria-checked', maestros[i].presente);

  atualizarTotais();
}

function atualizarTotais() {
  const ado = congregacoes.reduce((s, c) => s + c.adolescentes, 0);
  const jov = congregacoes.reduce((s, c) => s + c.jovens, 0);
  const mae = maestros.filter(m => m.presente).length;

  $('r-ado').textContent = ado;
  $('r-jov').textContent = jov;
  $('r-mae').textContent = `${mae}/${maestros.length}`;
  $('badge-total').textContent = ado + jov + mae;
}


/* ============================================
   Salvar chamada
   ============================================ */

$('btn-salvar').addEventListener('click', salvar);

async function salvar() {
  const btn = $('btn-salvar');
  limparAviso();

  if (!$('data').value) {
    return aviso('erro', 'Escolha a data do ensaio antes de salvar.');
  }

  const corpo = {
    data: $('data').value,
    congregacoes: congregacoes.map(c => ({
      congregacaoId: c.id,
      qtdAdolescentes: c.adolescentes,
      qtdJovens: c.jovens
    })),
    maestros: maestros.map(m => ({
      maestroId: m.id,
      presente: m.presente
    }))
  };

  btn.disabled = true;
  btn.textContent = 'Salvando…';

  try {
    const r = await fetch(API + '/ensaios', {
      method: 'POST',
      headers: { 'Content-Type': 'application/json' },
      body: JSON.stringify(corpo)
    });

    if (r.status === 201) {
      aviso('ok', 'Chamada salva.');
      zerar();
    } else {
      const erro = await r.json().catch(() => null);
      aviso('erro', erro?.mensagem || erro?.message || 'Não foi possível salvar a chamada.');
    }

  } catch (e) {
    aviso('erro', 'Sem conexão com o servidor. Verifique se ele está no ar e tente de novo.');

  } finally {
    btn.disabled = false;
    btn.textContent = 'Salvar chamada';
  }
}

function zerar() {
  congregacoes.forEach(c => { c.adolescentes = 0; c.jovens = 0; });
  maestros.forEach(m => m.presente = false);

  renderCongregacoes();
  renderMaestros();
  atualizarTotais();
}


/* ============================================
   Avisos
   ============================================ */

function aviso(tipo, texto) {
  $('mensagem').innerHTML = `<div class="aviso ${tipo}">${texto}</div>`;
  window.scrollTo({ top: 0, behavior: 'smooth' });

  if (tipo === 'ok') setTimeout(limparAviso, 4000);
}

function limparAviso() {
  $('mensagem').innerHTML = '';
}


/* ============================================
   Início
   ============================================ */

$('data').valueAsDate = new Date();
carregar();
