// src/services/supportService.js
const Support = require('../models/Support');
const Log = require('../models/Log');

exports.getApoio = async (usuarioAnonimo) => {
  // Log do acesso
  await Log.create({ evento: "getApoio", usuarioAnonimo });

  return await Support.find();
};
