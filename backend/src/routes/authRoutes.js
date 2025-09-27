// src/routes/authRoutes.js
const express = require('express');
const router = express.Router();
const jwt = require('jsonwebtoken');

// POST /api/auth → gera token JWT anônimo
router.post('/', (req, res) => {
  const { usuarioAnonimo } = req.body;

  if (!usuarioAnonimo) {
    return res.status(400).json({ msg: 'Informe usuarioAnonimo' });
  }

  const token = jwt.sign({ usuarioAnonimo }, process.env.JWT_SECRET, { expiresIn: '1h' });
  res.json({ token });
});

module.exports = router;
