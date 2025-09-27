const mongoose = require('mongoose');

const SelfCheckSchema = new mongoose.Schema({
  mood: { type: String, required: true },
  note: { type: String },
  createdAt: { type: Date, default: Date.now },
  usuarioAnonimo: { type: String, required: true }
});

module.exports = mongoose.model('SelfCheck', SelfCheckSchema);
