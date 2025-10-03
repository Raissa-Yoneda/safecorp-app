const express = require("express");
const router = express.Router();
const auth = require("../middleware/auth"); // middleware JWT
const { getAlertas } = require("../controllers/alertController");

// GET /api/alertas: retorna os alertas
router.get("/", auth, getAlertas);

module.exports = router;
