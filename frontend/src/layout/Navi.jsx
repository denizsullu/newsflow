import { Link } from "react-router-dom";

function Navi() {
    return (
        <header className="topbar">
            <Link to="/" className="brand" aria-label="NewsFlow ana sayfa">
                <span className="brand-mark">NF</span>
                <span className="brand-copy">
                    <strong>NewsFlow</strong>
                    <small>Local newsroom</small>
                </span>
            </Link>
            <nav className="topbar-nav" aria-label="Ana navigasyon">
                <a href="http://localhost:8080/swagger-ui/index.html" target="_blank" rel="noreferrer">
                    API
                </a>
                <a href="http://localhost:5050" target="_blank" rel="noreferrer">
                    DB
                </a>
            </nav>
        </header>
    );
}

export default Navi;
