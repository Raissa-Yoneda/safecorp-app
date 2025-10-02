// backend/src/controllers/selfCheckController.js
const SelfCheck = require('../models/SelfCheck');

// Registrar check-in de humor
exports.createSelfCheck = async (req, res) => {
  try {
    const usuarioAnonimo = req.usuarioAnonimo; // obtido do middleware auth
    const selfCheck = await SelfCheck.create({ ...req.body, usuarioAnonimo });
    res.status(201).json({ message: "Check-in de humor registrado com sucesso", selfCheck });
  } catch (err) {
    console.error(err);
    res.status(500).json({ error: err.message });
  }
};

// Listar check-ins de humor
exports.getSelfChecks = async (req, res) => {
  try {
    const historico = await SelfCheck.find().sort({ createdAt: -1 });
    res.json(historico);
  } catch (err) {
    console.error(err);
    res.status(500).json({ error: err.message });
  }
};
