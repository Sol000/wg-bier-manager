FROM oven/bun
COPY . .
RUN bun install
RUN bun run build

ENTRYPOINT [ "bun" ]
CMD [ "./build/index.js"]
