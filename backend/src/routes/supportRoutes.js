const express = require('express');
const router = express.Router();
const auth = require('../middleware/auth');
const { getApoio } = require('../controllers/supportController');

// antes: router.get('/apoio', auth, getApoio);
// depois: apenas router.get('/', auth, getApoio);
router.get('/', auth, getApoio); // GET /api/apoio

module.exports = router;
