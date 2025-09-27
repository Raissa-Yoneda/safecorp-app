const SelfCheck = require('../models/SelfCheck');

exports.createHumor = async (req, res) => {
  try {
    const usuarioAnonimo = req.usuarioAnonimo;
    const humor = await SelfCheck.create({ ...req.body, usuarioAnonimo });
    res.status(201).json({ message: "Humor registrado com sucesso", humor });
  } catch (err) {
    console.error(err);
    res.status(500).json({ error: err.message });
  }
};

exports.getHumor = async (req, res) => {
  try {
    const historico = await SelfCheck.find().sort({ createdAt: -1 });
    res.json(historico);
  } catch (err) {
    console.error(err);
    res.status(500).json({ error: err.message });
  }
};
