const express = require('express');
const router = express.Router();
const auth = require('../middleware/auth');
const { getApoio } = require('../controllers/supportController');

router.get('/apoio', auth, getApoio);

module.exports = router;
