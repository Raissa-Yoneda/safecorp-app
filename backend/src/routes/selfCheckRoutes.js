const express = require("express");
const router = express.Router();
const auth = require("../middleware/auth");
const { createSelfCheck, getSelfChecks } = require("../controllers/selfCheckController");

// POST /api/humor: registra um check-in de humor
router.post("/", auth, createSelfCheck);

// GET /api/humor: lista os check-ins
router.get("/", auth, getSelfChecks);

module.exports = router;

