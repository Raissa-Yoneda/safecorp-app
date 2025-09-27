const Avaliacao = require('../models/Avaliacao');

exports.createAvaliacao = async (req, res) => {
  try {
    const usuarioAnonimo = req.usuarioAnonimo;
    const avaliacao = await Avaliacao.create({ ...req.body, usuarioAnonimo });
    res.status(201).json({ message: "Avaliação salva com sucesso", avaliacao });
  } catch (err) {
    console.error(err);
    res.status(500).json({ error: err.message });
  }
};

exports.getAvaliacoes = async (req, res) => {
  try {
    const avaliacoes = await Avaliacao.find().sort({ createdAt: -1 });
    res.json(avaliacoes);
  } catch (err) {
    console.error(err);
    res.status(500).json({ error: err.message });
  }
};
