import { useEffect, useState } from "react";
import { Link, useParams } from "react-router-dom";
import NewsService from "../services/newsService.js";

const newsService = new NewsService();
const fallbackImage = "/601eeb63-6542-4a1b-b323-a23303f48d55.webp";

function formatDate(value) {
    if (!value) return "";

    return new Date(value).toLocaleString("tr-TR", {
        day: "2-digit",
        month: "long",
        year: "numeric",
        hour: "2-digit",
        minute: "2-digit",
    });
}

function getSourceClass(publisher) {
    if (publisher === "Sozcu") return "source-pill sozcu";
    if (publisher === "NTV") return "source-pill ntv";
    if (publisher === "BBC") return "source-pill bbc";
    return "source-pill";
}

function NewsDetail() {
    const { id } = useParams();
    const [news, setNews] = useState(null);
    const [imageSrc, setImageSrc] = useState(fallbackImage);
    const [loading, setLoading] = useState(true);
    const [error, setError] = useState("");

    useEffect(() => {
        let ignore = false;

        setLoading(true);
        setError("");

        newsService
            .getById(id)
            .then((result) => {
                if (!ignore) {
                    const nextNews = result.data.data;
                    setNews(nextNews);
                    setImageSrc(nextNews?.imageUrl || fallbackImage);
                }
            })
            .catch(() => {
                if (!ignore) {
                    setError("Haber bulunamadi.");
                }
            })
            .finally(() => {
                if (!ignore) {
                    setLoading(false);
                }
            });

        return () => {
            ignore = true;
        };
    }, [id]);

    if (loading) {
        return (
            <main className="detail-shell">
                <section className="state-panel">
                    <span className="loader" />
                    <p>Haber yukleniyor</p>
                </section>
            </main>
        );
    }

    if (error || !news) {
        return (
            <main className="detail-shell">
                <section className="state-panel error">
                    <p>{error || "Haber bulunamadi."}</p>
                    <Link to="/" className="read-link">
                        Akisa don
                    </Link>
                </section>
            </main>
        );
    }

    return (
        <main className="detail-shell">
            <Link to="/" className="back-link">
                Akisa don
            </Link>

            <article className="detail-article">
                <header className="detail-header">
                    <div className="detail-meta">
                        <span className={getSourceClass(news.publisher)}>{news.publisher}</span>
                        <time>{formatDate(news.publishedDate)}</time>
                    </div>
                    <h1>{news.title}</h1>
                </header>

                <figure className="detail-media">
                    <img
                        src={imageSrc}
                        alt={news.title}
                        onError={() => setImageSrc(fallbackImage)}
                    />
                </figure>

                <section className="detail-body">
                    <p>{news.content}</p>
                    <a href={news.link} target="_blank" rel="noopener noreferrer" className="source-link">
                        Orijinal kaynaga git
                    </a>
                </section>
            </article>
        </main>
    );
}

export default NewsDetail;
