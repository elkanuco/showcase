const express = require("express");
const app = express();
const PORT = process.env.PORT || 3000;
// In-memory cache: Map of "{base}-{target}-{date}" to exchange rate
const exchangeRateCache = new Map();
app.use(express.json());
app.get("/exchange-rate", (req, res) => {
  const { base, target } = req.query;
  if (!base || !target || base.length!=3 || target.length!=3) {
    return res.status(400).json({ error: "Wrong 'base' or 'target' parameters" });
  }
   // Get current date in YYYY-MM-DD format
  const today = new Date().toISOString().split("T")[0];
  // Create cache key: e.g., "USD-EUR-2025-07-26"
  const cacheKey = `${base.toUpperCase()}-${target.toUpperCase()}-${today}`;
  // Check cache for existing rate
  let rate = exchangeRateCache.get(cacheKey);
  if (!rate) {
    // Random number between 0 (not included) and 3 (included), with 3 decimal places
    // Number.EPSILON = 2^-52 , small number to prevent 0  
    rate = +(Math.random() * 3 + Number.EPSILON).toFixed(3);
      // Store in cache
      exchangeRateCache.set(cacheKey, rate);
  }

  const result = {
    base: base.toUpperCase(),
    target: target.toUpperCase(),
    rate: rate
  };
  console.info(result);
  res.json(result);
});
app.listen(PORT, () => {
  console.info(`Mock currency service running on port ${PORT}`);
});
