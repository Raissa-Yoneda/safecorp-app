const express = require('express');
const router = express.Router();
const auth = require('../middleware/auth');
const { createAvaliacao, getAvaliacoes } = require('../controllers/avaliacaoController');

// antes: router.post('/avaliacoes', auth, createAvaliacao);
// depois: apenas router.post('/', auth, createAvaliacao);
router.post('/', auth, createAvaliacao); // POST /api/avaliacoes
router.get('/', auth, getAvaliacoes);   // GET /api/avaliacoes

module.exports = router;
