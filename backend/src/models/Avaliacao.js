const mongoose = require('mongoose');

const AvaliacaoSchema = new mongoose.Schema({
  respostas: { type: Object, required: true }, // pode ser JSON com perguntas/respostas
  createdAt: { type: Date, default: Date.now },
  usuarioAnonimo: { type: String, required: true }
});

module.exports = mongoose.model('Avaliacao', AvaliacaoSchema);
