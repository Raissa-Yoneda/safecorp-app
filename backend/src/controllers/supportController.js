const supportService = require('../services/supportService');

exports.getApoio = async (req, res) => {
  try {
    const usuarioAnonimo = req.usuarioAnonimo;
    const recursos = await supportService.getApoio(usuarioAnonimo);

    res.json(recursos);
  } catch (err) {
    console.error(err);
    res.status(500).json({ error: err.message });
  }
};
