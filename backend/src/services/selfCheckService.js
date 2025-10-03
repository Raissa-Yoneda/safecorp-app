const SelfCheck = require('../models/SelfCheck');
const Log = require('../models/Log');

exports.createHumor = async (usuarioAnonimo, data) => {
  const selfCheck = await SelfCheck.create({ ...data, usuarioAnonimo });

  // Log do evento
  await Log.create({ evento: "createHumor", usuarioAnonimo, detalhes: data });

  return selfCheck;
};

exports.getHumor = async (usuarioAnonimo) => {
  // Log do acesso
  await Log.create({ evento: "getHumor", usuarioAnonimo });

  return await SelfCheck.find({ usuarioAnonimo }).sort({ createdAt: -1 });
};
