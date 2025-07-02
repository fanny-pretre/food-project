import { Link } from "react-router-dom";

const FoodItem = ({ name, description, price, imageUrl, id }) => {
  console.log(name);
  return (
    <div className="col-12 col-sm-6 col-md-4 col-lg-3 mb-4 d-flex justify-content-center">
      <div className="card w-100">
        <Link to={`/food/${id}`}>
          <img
            src={imageUrl}
            className="card-img-top"
            alt="Product Image"
            height={300}
            width={60}
            style={{ objectFit: "cover" }}
          />
        </Link>
        <div className="card-body">
          <h5 className="card-title">{name}</h5>
          <p className="card-text">{description}</p>
          <div className="d-flex justify-content-between align-items-center">
            <span className="h5 mb-0">{price}&euro;</span>
            <div>
              <i className="bi bi-star-fill text-warning"></i>
              <i className="bi bi-star-fill text-warning"></i>
              <i className="bi bi-star-fill text-warning"></i>
              <i className="bi bi-star-fill text-warning"></i>
              <i className="bi bi-star-half text-warning"></i>
              <small className="text-muted">(4.5)</small>
            </div>
          </div>
        </div>
        <div className="card-footer d-flex justify-content-between bg-light">
          <Link className="btn btn-primary btn-sm" to={`food/${id}`}>
            View Food
          </Link>

          <button className="btn btn-primary btn-sm">
            <i className="bi bi-plus-circle"></i>
          </button>
        </div>
      </div>
    </div>
  );
};

export default FoodItem;
