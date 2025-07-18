import React, { useState } from "react";
import FoodDisplay from "../../components/FoodDisplay/FoodDisplay";

const ExploreFood = () => {
  const [category, setCategory] = useState("All");
  const [searchText, setSearchText] = useState("");
  return (
    <>
      <div className="container">
        <div className="row justify-content-center">
          <div className="col-md-6">
            <form onSubmit={(e) => e.preventDefault()}>
              <div className="input-group mb-3">
                <select
                  className="form-select mt-2"
                  onChange={(e) => setCategory(e.target.value)}
                  style={{ "max-width": "150" }}
                >
                  <option value="All"> All </option>
                  <option value="Biryani"> Biryani </option>
                  <option value="Cake"> Cake </option>
                  <option value="Burger"> Burger </option>
                  <option value="Pizza"> Pizza </option>
                  <option value="Salad"> Salad </option>
                  <option value="Ice Cream"> Ice cream </option>
                  <option value="Noodles"> Noodles </option>
                </select>
                <input
                  type="text"
                  className="form-control mt-2"
                  placeholder="Search your favorite dish..."
                  onChange={(e) => setSearchText(e.target.value)}
                  value={searchText}
                />
                <button className="btn btn-primary mt-2" type="submit">
                  <i className="bi bi-search"></i>
                </button>
              </div>
            </form>
          </div>
        </div>
      </div>
      <FoodDisplay category={category} searchText={searchText} />
    </>
  );
};

export default ExploreFood;
