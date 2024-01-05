import axios from "axios";

class NewsService{
    getAllNews() {
        return axios.get("/api/news/getAllNewsResponses")
    }
    getByTitle(id){
        return axios.get(`/api/news/findByUUID?uuid=${id}`)
    }
}
export default NewsService;