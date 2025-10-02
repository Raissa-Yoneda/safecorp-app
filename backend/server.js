// backend/server.js
const express = require("express");
const dotenv = require("dotenv");
const morgan = require("morgan");
const cors = require("cors");
const connectDB = require("./src/config/db");

// importar rotas
const avaliacaoRoutes = require("./src/routes/avaliacaoRoutes");
const selfCheckRoutes = require("./src/routes/selfCheckRoutes");
const supportRoutes = require("./src/routes/supportRoutes");
const authRoutes = require("./src/routes/authRoutes"); // rota para gerar token JWT
const alertRoutes = require("./src/routes/alertRoutes");

dotenv.config();

const app = express();

// middlewares
app.use(express.json());
app.use(cors());
app.use(morgan("dev"));

// conectar no MongoDB
connectDB();

// rota raiz
app.get("/", (req, res) => {
  res.json({ message: "SafeCorp API rodando 🚀" });
});

// rotas da API
app.use("/api/auth", authRoutes);            // POST /api/auth
app.use("/api/avaliacoes", avaliacaoRoutes); // POST/GET /api/avaliacoes
app.use("/api/humor", selfCheckRoutes);      // POST/GET /api/humor
app.use("/api/apoio", supportRoutes);        // GET /api/apoio
app.use("/api/alertas", alertRoutes);        // GET /api/alertas

// tratar rotas não encontradas
app.use((req, res) => {
  res.status(404).json({ error: "Rota não encontrada" });
});

const PORT = process.env.PORT || 3000;
app.listen(PORT, () => console.log(`🚀 Servidor rodando na porta ${PORT}`));
