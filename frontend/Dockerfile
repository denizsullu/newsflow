# ---------- Build Stage ----------
FROM node:20-alpine AS build
# pnpm için corepack
RUN corepack enable

WORKDIR /app
COPY package.json pnpm-lock.yaml ./
RUN pnpm install --frozen-lockfile

COPY . .
RUN pnpm build   # veya: pnpm run build

# ---------- Runtime Stage ----------
FROM nginx:1.27-alpine
# Nginx konfigürünü kopyala (proxy vs. varsa)
COPY nginx.conf /etc/nginx/conf.d/default.conf

# Statik dosyaları kopyala
COPY --from=build /app/dist /usr/share/nginx/html

EXPOSE 80
CMD ["nginx", "-g", "daemon off;"]
