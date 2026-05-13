import { useEffect, useMemo, useState } from "react";
import { Link } from "react-router-dom";
import NewsService from "../services/newsService.js";

const newsService = new NewsService();
const pageSizes = [8, 12, 16];
const publishers = ["Sozcu", "NTV", "BBC"];
const fallbackImage = "/601eeb63-6542-4a1b-b323-a23303f48d55.webp";

const publisherMeta = {
    Sozcu: {
        label: "Sozcu",
        short: "SZ",
        accent: "bg-[#da3d2a] text-white",
        soft: "bg-[#fff1ed] text-[#a72b1d]",
    },
    NTV: {
        label: "NTV",
        short: "NT",
        accent: "bg-[#19a7a1] text-[#062725]",
        soft: "bg-[#e7fbf8] text-[#0d6864]",
    },
    BBC: {
        label: "BBC",
        short: "BC",
        accent: "bg-[#202733] text-white",
        soft: "bg-[#eef1f5] text-[#202733]",
    },
};

function formatDate(value) {
    if (!value) return "";

    return new Date(value).toLocaleString("tr-TR", {
        day: "2-digit",
        month: "short",
        year: "numeric",
        hour: "2-digit",
        minute: "2-digit",
    });
}

function excerpt(value, limit = 150) {
    if (!value) return "";
    return value.length > limit ? `${value.slice(0, limit).trim()}...` : value;
}

function PublisherMark({ publisher, compact = false }) {
    const meta = publisherMeta[publisher] || {
        label: publisher || "Kaynak",
        short: "NF",
        accent: "bg-[#2b2118] text-white",
        soft: "bg-[#f4efe7] text-[#2b2118]",
    };

    return (
        <span className="flex items-center gap-2">
            <span className={`source-mark ${meta.accent}`}>{meta.short}</span>
            {!compact && <span className="source-name">{meta.label}</span>}
        </span>
    );
}

function NewsImage({ item, large = false }) {
    const [src, setSrc] = useState(item.imageUrl || fallbackImage);

    useEffect(() => {
        setSrc(item.imageUrl || fallbackImage);
    }, [item.imageUrl]);

    return (
        <img
            src={src}
            loading="lazy"
            alt={item.title}
            onError={() => setSrc(fallbackImage)}
            className={`news-image ${large ? "news-image-large" : ""}`}
        />
    );
}

function Pagination({ currentPage, totalPages, onPageChange }) {
    const pages = useMemo(() => {
        const last = Math.max(totalPages - 1, 0);
        const start = Math.max(0, Math.min(currentPage - 2, last - 4));
        const end = Math.min(last, start + 4);

        return Array.from({ length: end - start + 1 }, (_, index) => start + index);
    }, [currentPage, totalPages]);

    if (totalPages <= 1) return null;

    return (
        <nav className="pagination" aria-label="Haber sayfalari">
            <button
                className="pagination-button"
                disabled={currentPage === 0}
                onClick={() => onPageChange(currentPage - 1)}
            >
                Onceki
            </button>
            <div className="pagination-pages">
                {pages.map((pageNumber) => (
                    <button
                        key={pageNumber}
                        className={`pagination-number ${pageNumber === currentPage ? "active" : ""}`}
                        onClick={() => onPageChange(pageNumber)}
                    >
                        {pageNumber + 1}
                    </button>
                ))}
            </div>
            <button
                className="pagination-button"
                disabled={currentPage >= totalPages - 1}
                onClick={() => onPageChange(currentPage + 1)}
            >
                Sonraki
            </button>
        </nav>
    );
}

function NewsCard({ item }) {
    return (
        <article className="news-card">
            <Link to={`/news/${item.id}`} className="news-card-media" aria-label={item.title}>
                <NewsImage item={item} />
            </Link>
            <div className="news-card-body">
                <div className="news-card-meta">
                    <PublisherMark publisher={item.publisher} compact />
                    <time>{formatDate(item.publishedDate)}</time>
                </div>
                <Link to={`/news/${item.id}`} className="news-card-title">
                    {item.title}
                </Link>
                <p className="news-card-copy">{excerpt(item.content)}</p>
            </div>
        </article>
    );
}

function LeadStory({ item }) {
    if (!item) return null;

    return (
        <section className="lead-story">
            <Link to={`/news/${item.id}`} className="lead-media" aria-label={item.title}>
                <NewsImage item={item} large />
            </Link>
            <div className="lead-content">
                <div className="lead-topline">
                    <PublisherMark publisher={item.publisher} />
                    <time>{formatDate(item.publishedDate)}</time>
                </div>
                <Link to={`/news/${item.id}`} className="lead-title">
                    {item.title}
                </Link>
                <p className="lead-copy">{excerpt(item.content, 260)}</p>
                <Link to={`/news/${item.id}`} className="read-link">
                    Haberi oku
                </Link>
            </div>
        </section>
    );
}

function News() {
    const [page, setPage] = useState(0);
    const [size, setSize] = useState(12);
    const [publisher, setPublisher] = useState("");
    const [pageData, setPageData] = useState(null);
    const [loading, setLoading] = useState(true);
    const [error, setError] = useState("");

    useEffect(() => {
        let ignore = false;

        setLoading(true);
        setError("");

        newsService
            .getNewsPage({ page, size, publisher })
            .then((result) => {
                if (!ignore) {
                    setPageData(result.data.data);
                }
            })
            .catch(() => {
                if (!ignore) {
                    setError("Haberler yuklenemedi.");
                    setPageData(null);
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
    }, [page, size, publisher]);

    const content = pageData?.content || [];
    const totalElements = pageData?.totalElements || 0;
    const totalPages = pageData?.totalPages || 0;
    const lead = content[0];
    const rest = content.slice(1);

    const changePublisher = (nextPublisher) => {
        setPublisher(nextPublisher);
        setPage(0);
    };

    const changeSize = (nextSize) => {
        setSize(nextSize);
        setPage(0);
    };

    return (
        <main className="news-shell">
            <section className="news-hero">
                <div>
                    <p className="eyebrow">Canli haber akis paneli</p>
                    <h1>NewsFlow</h1>
                    <p className="hero-copy">
                        BBC, NTV ve Sozcu haberleri tek akista; sayfa sayfa, daha hizli ve daha temiz.
                    </p>
                </div>
                <div className="hero-metrics">
                    <span>{totalElements}</span>
                    <small>{publisher ? `${publisher} haberi` : "toplam haber"}</small>
                </div>
            </section>

            <section className="toolbar" aria-label="Haber filtreleri">
                <div className="source-tabs">
                    <button
                        className={`source-tab ${publisher === "" ? "active" : ""}`}
                        onClick={() => changePublisher("")}
                    >
                        Tum kaynaklar
                    </button>
                    {publishers.map((source) => (
                        <button
                            key={source}
                            className={`source-tab ${publisher === source ? "active" : ""}`}
                            onClick={() => changePublisher(source)}
                        >
                            <PublisherMark publisher={source} compact />
                            {source}
                        </button>
                    ))}
                </div>

                <div className="page-size-control" aria-label="Sayfa boyutu">
                    {pageSizes.map((value) => (
                        <button
                            key={value}
                            className={value === size ? "active" : ""}
                            onClick={() => changeSize(value)}
                        >
                            {value}
                        </button>
                    ))}
                </div>
            </section>

            {loading && (
                <section className="state-panel">
                    <span className="loader" />
                    <p>Haberler yukleniyor</p>
                </section>
            )}

            {!loading && error && (
                <section className="state-panel error">
                    <p>{error}</p>
                </section>
            )}

            {!loading && !error && content.length === 0 && (
                <section className="state-panel">
                    <p>Bu kaynak icin haber bulunamadi.</p>
                </section>
            )}

            {!loading && !error && content.length > 0 && (
                <>
                    <LeadStory item={lead} />
                    <section className="news-grid" aria-label="Haber listesi">
                        {rest.map((item) => (
                            <NewsCard key={item.id} item={item} />
                        ))}
                    </section>
                    <Pagination currentPage={page} totalPages={totalPages} onPageChange={setPage} />
                </>
            )}
        </main>
    );
}

export default News;
