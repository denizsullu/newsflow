import axios from "axios";

const api = axios.create({
    baseURL: import.meta.env.VITE_API_BASE_URL || "",
});

class NewsService {
    getNewsPage({ page = 0, size = 12, publisher = "" } = {}) {
        return api.get("/api/news/getNewsPageable", {
            params: {
                page,
                size,
                ...(publisher ? { publisher } : {}),
            },
        });
    }

    getById(id) {
        return api.get("/api/news/findByUUID", {
            params: { uuid: id },
        });
    }
}

export default NewsService;
