
import {Route, Routes} from "react-router-dom";
import Navi from "./Navi.jsx";
import News from "../pages/News.jsx";
import NewsDetail from "../pages/NewsDetail.jsx";

function MainDashboard() {
    return (
        <>
            <Navi/>
            <Routes>
                <Route path="/" element={<News/>}/>
                <Route path="/news/:id" element={<NewsDetail/>}/>
            </Routes>


        </>

    );
}

export default MainDashboard;