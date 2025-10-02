const selfCheckService = require('../services/selfCheckService');

exports.createSelfCheck = async (req, res) => {
  try {
    const usuarioAnonimo = req.usuarioAnonimo;
    const selfCheck = await selfCheckService.createHumor(usuarioAnonimo, req.body);

    res.status(201).json({ message: "Check-in de humor registrado com sucesso", selfCheck });
  } catch (err) {
    console.error(err);
    res.status(500).json({ error: err.message });
  }
};

exports.getSelfChecks = async (req, res) => {
  try {
    const usuarioAnonimo = req.usuarioAnonimo;
    const historico = await selfCheckService.getHumor(usuarioAnonimo);

    res.json(historico);
  } catch (err) {
    console.error(err);
    res.status(500).json({ error: err.message });
  }
};
