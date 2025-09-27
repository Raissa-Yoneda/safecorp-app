const express = require('express');
const router = express.Router();
const auth = require('../middleware/auth');
const { createHumor, getHumor } = require('../controllers/selfCheckController');

router.post('/humor', auth, createHumor);
router.get('/humor', auth, getHumor);

module.exports = router;
