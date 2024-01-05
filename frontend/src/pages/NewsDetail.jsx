import {useParams} from "react-router-dom";
import {useEffect, useState} from "react";
import NewsService from "../services/newsService.js";

function NewsDetail() {
    let {id} = useParams()
    const [news, setNews] = useState({})
    useEffect(() => {
        let newsService = new NewsService()
        newsService.getByTitle(id).then(result => setNews(result.data.data))
    }, []);
    const date = new Date(news.publishedDate);
    const formattedDate = date.toLocaleString('tr-TR', {
        year: 'numeric',
        month: 'long',
        day: 'numeric',
        hour: '2-digit',
        minute: '2-digit'
    });
    return (
        <div className="bg-white py-6 sm:py-8 lg:py-12">
            <div className="mx-auto max-w-screen-xl px-4 md:px-8">
                <div className="grid gap-8 md:grid-cols-10 lg:gap-12">
                    <div className="md:col-span-4">
                        <div className="h-64 overflow-hidden rounded-lg bg-gray-100 shadow-lg md:h-auto">
                            <img
                                src={news.imageUrl ? news.imageUrl : '/public/601eeb63-6542-4a1b-b323-a23303f48d55.webp'}
                                loading="lazy" alt={news.title}
                                className="h-full w-full object-cover object-center"/>
                        </div>
                    </div>

                    <div className="md:pt-8 md:col-span-6">

                        <h1 className="mb-4 text-center text-2xl font-bold text-gray-800 sm:text-3xl md:mb-6 md:text-left">{news.title}</h1>

                        <div className="text-center font-medium text-indigo-500 md:text-left">
                            Yayınlanma Tarihi: {formattedDate}
                        </div>
                        <p className={`text-gray-500 sm:text-lg  ${news.content && news.content.length > 1000 ? 'max-h-96 overflow-y-auto scrollbar-custom' : ''}`}>
                            {news.content || 'İçerik yükleniyor...'}<br/><br/>
                        </p>
                      <div className="flex justify-between items-baseline">
                        <a
                            href={news.link}
                            target="_blank"
                            rel="noopener noreferrer"
                            className="bg-transparent hover:bg-blue-500 text-blue-700 font-semibold hover:text-white py-2 px-4 border border-blue-500 hover:border-transparent rounded ease-in-out focus:outline-none focus:ring focus:ring-blue-300">
                            Haber Kaynağına Git
                        </a>
                          <p className="text-center font-bold text-indigo-500 md:text-left mt-4 items-center">{news.publisher}</p>
                    </div>





                    </div>
                </div>
            </div>
        </div>
    )
}

export default NewsDetail;