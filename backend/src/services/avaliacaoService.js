// src/services/avaliacaoService.js
const Avaliacao = require('../models/Avaliacao');
const Log = require('../models/Log');

exports.createAvaliacao = async (usuarioAnonimo, respostas) => {
  const avaliacao = await Avaliacao.create({ respostas, usuarioAnonimo });

  // Log do evento
  await Log.create({ evento: "createAvaliacao", usuarioAnonimo, detalhes: respostas });

  return avaliacao;
};

exports.getAvaliacoes = async (usuarioAnonimo) => {
  // Log do acesso
  await Log.create({ evento: "getAvaliacoes", usuarioAnonimo });

  return await Avaliacao.find({ usuarioAnonimo }).sort({ createdAt: -1 });
};
