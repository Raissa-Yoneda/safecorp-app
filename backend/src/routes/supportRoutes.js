const express = require('express');
const router = express.Router();
const auth = require('../middleware/auth');
const { getApoio } = require('../controllers/supportController');

router.get('/', auth, getApoio); // GET /api/apoio

module.exports = router;
