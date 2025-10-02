const avaliacaoService = require('../services/avaliacaoService');

exports.createAvaliacao = async (req, res) => {
  try {
    const usuarioAnonimo = req.usuarioAnonimo;
    const respostas = req.body.respostas;

    const avaliacao = await avaliacaoService.createAvaliacao(usuarioAnonimo, respostas);

    res.status(201).json({ message: "Avaliação salva com sucesso", avaliacao });
  } catch (err) {
    console.error(err);
    res.status(500).json({ error: err.message });
  }
};

exports.getAvaliacoes = async (req, res) => {
  try {
    const usuarioAnonimo = req.usuarioAnonimo;
    const avaliacoes = await avaliacaoService.getAvaliacoes(usuarioAnonimo);

    res.json(avaliacoes);
  } catch (err) {
    console.error(err);
    res.status(500).json({ error: err.message });
  }
};
