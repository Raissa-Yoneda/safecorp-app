// src/services/alertService.js
const Log = require('../models/Log');

const alertas = [
  { mensagem: "Lembre-se de fazer uma pausa de 5 min a cada hora." },
  { mensagem: "Beba água regularmente." }
];

exports.getAlertas = async (usuarioAnonimo) => {
  // Log do acesso
  await Log.create({ evento: "getAlertas", usuarioAnonimo });

  return alertas;
};
