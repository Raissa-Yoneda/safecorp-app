const express = require('express');
const router = express.Router();
const auth = require('../middleware/auth');
const { createAvaliacao, getAvaliacoes } = require('../controllers/avaliacaoController');

router.post('/avaliacoes', auth, createAvaliacao); // ✅ função passada corretamente
router.get('/avaliacoes', auth, getAvaliacoes);

module.exports = router;
