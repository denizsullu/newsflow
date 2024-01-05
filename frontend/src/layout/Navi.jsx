import {Link} from "react-router-dom";

function Navi() {
    return (
        <>

            <nav className="bg-blue-900 h-16 flex items-center justify-center px-6 shadow-md rounded-b-3xl gap-4 mb-10">

                <Link to={`/`}> <img
                    src="/4d9a1147-fd94-45fa-8fd7-9a1c455b84bf.webp"
                    alt="Logo" className="h-24 rounded-full mt-10 "/> </Link>

        </nav>
</>

)
    ;
}

export default Navi;
