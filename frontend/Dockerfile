FROM oven/bun
COPY . .
RUN bun install
RUN bun run build

CMD ["bun", "./build/index.js"]
