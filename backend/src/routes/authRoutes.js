// backend/src/routes/authRoutes.js
const express = require('express');
const router = express.Router();
const jwt = require('jsonwebtoken');

// POST /api/auth → gera token JWT anônimo
router.post('/', (req, res) => {
  const { usuarioAnonimo } = req.body;

  if (!usuarioAnonimo) {
    return res.status(400).json({ msg: 'Informe usuarioAnonimo' });
  }

  try {
    const token = jwt.sign(
      { usuarioAnonimo },
      process.env.JWT_SECRET, // certifique-se de ter JWT_SECRET no seu .env
      { expiresIn: '1h' }
    );

    res.json({ token });
  } catch (err) {
    console.error(err);
    res.status(500).json({ msg: 'Erro ao gerar token' });
  }
});

module.exports = router;
