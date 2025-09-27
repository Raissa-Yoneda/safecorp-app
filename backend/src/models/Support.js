const mongoose = require('mongoose');

const SupportSchema = new mongoose.Schema({
  titulo: { type: String, required: true },
  link: { type: String },
  descricao: { type: String }
});

module.exports = mongoose.model('Support', SupportSchema);
