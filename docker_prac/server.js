const http = require('http');
const fs = require('fs');
const path = require('path');

const PORT = process.env.PORT || 3000;

// In-memory storage
let thoughts = [];

const server = http.createServer((req, res) => {
  if (req.method === 'GET' && req.url === '/') {
    const filePath = path.join(__dirname, 'index.html');
    fs.readFile(filePath, (err, data) => {
      if (err) {
        res.writeHead(500);
        return res.end('Error loading UI');
      }
      res.writeHead(200, { 'Content-Type': 'text/html' });
      res.end(data);
    });
  }

  else if (req.method === 'GET' && req.url === '/api/thoughts') {
    res.writeHead(200, { 'Content-Type': 'application/json' });
    res.end(JSON.stringify(thoughts));
  }

  else if (req.method === 'POST' && req.url === '/api/thoughts') {
    let body = '';

    req.on('data', chunk => {
      body += chunk.toString();
    });

    req.on('end', () => {
      try {
        const { text } = JSON.parse(body);
        if (text) {
          thoughts.unshift({
            text,
            time: new Date().toISOString()
          });
        }
        res.writeHead(200);
        res.end('OK');
      } catch {
        res.writeHead(400);
        res.end('Invalid JSON');
      }
    });
  }

  else {
    res.writeHead(404);
    res.end('Not found');
  }
});

server.listen(PORT, () => {
  console.log(`Server running on http://localhost:${PORT}`);
});