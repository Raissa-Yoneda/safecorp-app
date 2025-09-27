const Support = require('../models/Support');

exports.getApoio = async (req, res) => {
  try {
    const recursos = await Support.find();
    res.json(recursos);
  } catch (err) {
    console.error(err);
    res.status(500).json({ error: err.message });
  }
};
