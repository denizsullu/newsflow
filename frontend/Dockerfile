# Temel imaj olarak Node.js'i kullan
FROM node:18-alpine

# pnpm'i global olarak yükle
RUN npm install -g pnpm

# Çalışma dizinini ayarla
WORKDIR /app

# Bağımlılıkları yüklemeden önce package.json, pnpm-lock.yaml dosyalarını kopyala
COPY package.json pnpm-lock.yaml ./

# pnpm kullanarak bağımlılıkları yükle
RUN pnpm install

# Proje dosyalarını kopyala
COPY . .

# Projeyi build et
RUN pnpm run build

# Prod modunda Vite'in build klasörünü sunmak için bir Nginx kullan
FROM nginx:alpine

# Build edilen dosyaları Nginx'e kopyala
COPY --from=0 /app/dist /usr/share/nginx/html

# Nginx konfigürasyonunu kopyala
COPY ./nginx.conf /etc/nginx/conf.d/default.conf

# Uygulamanın sunulacağı portu belirt
EXPOSE 80

# Nginx'i başlat
CMD ["nginx", "-g", "daemon off;"]
