import axios from "axios";

class NewsService{
    getAllNews() {
        return axios.get("https://api.denizsullu.com/api/news/getAllNewsResponses")
    }
    getByTitle(id){
        return axios.get(`https://api.denizsullu.com/api/news/findByUUID?uuid=${id}`)
    }
}
export default NewsService;