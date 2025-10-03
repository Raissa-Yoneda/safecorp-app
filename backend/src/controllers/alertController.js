const Log = require('../models/Log');

exports.getAlertas = async (req, res) => {
  try {
    const usuarioAnonimo = req.usuarioAnonimo;

    // Log do acesso
    await Log.create({ evento: "getAlertas", usuarioAnonimo });

    const alertas = [
      { mensagem: "Lembre-se de fazer uma pausa de 5 min a cada hora." },
      { mensagem: "Beba água regularmente." }
    ];

    res.json(alertas);
  } catch (err) {
    console.error(err);
    res.status(500).json({ error: err.message });
  }
};
