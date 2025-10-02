const mongoose = require('mongoose');

const LogSchema = new mongoose.Schema({
  evento: { type: String, required: true },
  usuarioAnonimo: { type: String },
  detalhes: { type: Object },
  createdAt: { type: Date, default: Date.now }
});

module.exports = mongoose.model('Log', LogSchema);
