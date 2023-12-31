import { useEffect, useState } from "react";
import "./styles/MyPhotoCardList.css";
import { DataView } from "primereact/dataview";

const MyPhotoCardList = ({ cards }) => {
  const [products, setProducts] = useState([]);
  const [layout, setLayout] = useState("grid");

  useEffect(() => {
    console.log("MyPhotoCardList Render.............");
    console.log("cards: ", cards);
    setProducts(cards);
  }, [cards]);

  const itemTemplate = (product, layout) => {
    if (!product) {
      return;
    }

    if (layout === "list") return listItem(product);
    else if (layout === "grid") return gridItem(product);
  };

  const gridItem = (product) => {
    return (
      <div className="col-12 sm:col-6 lg:col-4 xl:col-4 p-1">
        <div className="p-4 border-1 shadow border-round flex flex-column align-items-center">
          <img src={product.photocardUrl} alt={product.photocardSeq} />
        </div>
      </div>
    );
  };

  return (
    <div>
      <div className="page-title">My Photo Card</div>
      <div className="card-container mt-3">
        <DataView
          value={products}
          itemTemplate={itemTemplate}
          layout={layout}
        />
      </div>
    </div>
  );
};

export default MyPhotoCardList;
