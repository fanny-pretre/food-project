import React, { useContext, useState } from "react";

import { toast } from "react-toastify";

import "./PlaceOrder.jsx";
import { StoreContext } from "../../context/StoreContext.jsx";
import { assets } from "../../assets/assets.js";
import { calculateCartTotals } from "../../util/cartUtils.js";
import axios from "axios";
import { useNavigate } from "react-router-dom";

const PlaceOrder = () => {
  const { foodList, quantities, setQuantities, token } =
    useContext(StoreContext);
  const navigate = useNavigate();

  const [data, setData] = useState({
    firstName: "",
    lastName: "",
    email: "",
    phoneNumber: "",
    address: "",
    state: "",
    city: "",
    zip: "",
  });

  const onChangeHandler = (event) => {
    const name = event.target.name;
    const value = event.target.value;
    setData((data) => ({ ...data, [name]: value }));
  };

  const onSubmitHandler = async (event) => {
    event.preventDefault();
    const orderData = {
      userAddress: `${data.firstName}, ${data.lastName}, ${data.address}, ${data.city}, ${data.state}, ${data.zip}`,
      phoneNumber: data.phoneNumber,
      email: data.email,
      orderedItems: cartItems.map((item) => ({
        foodId: item.foodId,
        quantity: quantities[item.id],
        price: item.price * quantities[item.id],
        category: item.category,
        imageUrl: item.imageUrl,
        description: item.description,
        name: item.name,
      })),
      amount: total.toFixed(2),
      orderStatus: "Preparing",
    };

    try {
      const response = await axios.post(
        "http://localhost:8080/api/orders/create",
        orderData,
        {
          headers: { Authorization: `Bearer ${token}` },
        }
      );
      if (response.status === 201) {
        // Normalement on initiate le paiement ici mais moi je vais juste mettre un toast.
        toast.success("Successful Payment - Feature in Progress ");
        await clearCart();
        navigate("/myorders");
      } else {
        await deleteOrder();
        toast.error("Unable to place order. Please try again");
        navigate("/");
      }
    } catch {
      toast.error("Unable to place order. Please try again");
    }
  };

  const deleteOrder = async (orderId) => {
    try {
      await axios.delete("http://localhost:8080/api/orders/" + orderId, {
        headers: { Authorization: `Bearer ${token}` },
      });
    } catch {
      toast.error("Somethiing went wrong. Contact support");
    }
  };

  const clearCart = async () => {
    try {
      await axios.delete("http://localhost:8080/api/cart/clear", {
        headers: { Authorization: `Bearer ${token}` },
      });
      setQuantities({});
    } catch {
      toast.error("Error while clearing the cart.");
    }
  };

  //cart items
  const cartItems = foodList.filter((food) => quantities[food.id] > 0);

  // calculation
  const { subtotal, shipping, tax, total } = calculateCartTotals(
    cartItems,
    quantities
  );

  return (
    <div className="container mt-4">
      <main>
        <div class="py-5 text-center">
          {" "}
          <img
            className="d-block mx-auto"
            src={assets.logo}
            alt=""
            width="98"
            height="98"
          />{" "}
        </div>
        <div className="row g-5">
          <div className="col-md-5 col-lg-4 order-md-last">
            <h4 className="d-flex justify-content-between align-items-center mb-3">
              <span className="text-primary">Your cart</span>
              <span className="badge bg-primary rounded-pill">
                {cartItems.length}
              </span>
            </h4>
            <ul className="list-group mb-3">
              {cartItems.map((item) => (
                <li className="list-group-item d-flex justify-content-between lh-sm">
                  <div>
                    <h6 className="my-0">{item.name}</h6>
                    <small className="text-body-secondary">
                      Qty: {quantities[item.id]}
                    </small>
                  </div>
                  <span className="text-body-secondary">
                    {item.price * quantities[item.id]}&euro;
                  </span>
                </li>
              ))}
              <li className="list-group-item d-flex justify-content-between">
                <div>
                  <span>Shipping</span>
                </div>
                <span>{subtotal === 0 ? 0.0 : shipping.toFixed(2)}&euro;</span>
              </li>
              <li className="list-group-item d-flex justify-content-between ">
                <div>
                  <span>Tax (10%)</span>
                </div>
                <span>{tax.toFixed(2)}&euro;</span>
              </li>
              <li className="list-group-item d-flex justify-content-between">
                <span>Total (EUR)</span>
                <strong>{total.toFixed(2)}&euro;</strong>
              </li>
            </ul>
          </div>

          <div className="col-md-7 col-lg-8">
            <h4 className="mb-3">Billing address</h4>
            <form className="needs-validation" onSubmit={onSubmitHandler}>
              <div className="row g-3">
                <div className="col-sm-6">
                  <label htmlFor="firstName" className="form-label">
                    First name
                  </label>
                  <input
                    type="text"
                    className="form-control"
                    id="firstName"
                    placeholder="Jane"
                    required
                    name="firstName"
                    onChange={onChangeHandler}
                    value={data.firstName}
                  />
                </div>
                <div className="col-sm-6">
                  <label htmlFor="lastName" className="form-label">
                    Last name
                  </label>
                  <input
                    type="text"
                    className="form-control"
                    id="lastName"
                    placeholder="Doe"
                    required
                    name="lastName"
                    onChange={onChangeHandler}
                    value={data.lastName}
                  />
                </div>
                <div className="col-12">
                  <label htmlFor="email" className="form-label">
                    Email
                  </label>
                  <div className="input-group has-validation">
                    <span className="input-group-text">@</span>
                    <input
                      type="email"
                      className="form-control"
                      id="email"
                      placeholder="Email"
                      required
                      name="email"
                      onChange={onChangeHandler}
                      value={data.email}
                    />
                  </div>
                </div>
                <div className="col-12">
                  <label htmlFor="phoneNumber" className="form-label">
                    Phone Number
                  </label>
                  <input
                    type="number"
                    className="form-control"
                    id="phoneNumber"
                    placeholder="0606060606"
                    required
                    name="phoneNumber"
                    onChange={onChangeHandler}
                    value={data.phoneNumber}
                  />
                </div>

                <div className="col-12">
                  <label htmlFor="address" className="form-label">
                    Address
                  </label>
                  <input
                    type="text"
                    className="form-control"
                    id="address"
                    placeholder="1234 Main St"
                    required
                    name="address"
                    onChange={onChangeHandler}
                    value={data.address}
                  />
                </div>

                <div className="col-md-5">
                  <label htmlFor="state" className="form-label">
                    State
                  </label>
                  <select
                    className="form-select"
                    id="state"
                    required
                    name="state"
                    onChange={onChangeHandler}
                    value={data.state}
                  >
                    <option value="">Choose...</option>
                    <option>Deux-Sèvres</option>
                  </select>
                </div>
                <div className="col-md-4">
                  <label htmlFor="city" className="form-label">
                    City
                  </label>
                  <select
                    className="form-select"
                    id="city"
                    required
                    name="city"
                    onChange={onChangeHandler}
                    value={data.city}
                  >
                    <option value="">Choose...</option>
                    <option>Niort</option>
                  </select>
                </div>
                <div className="col-md-3">
                  <label htmlFor="zip" className="form-label">
                    Zip
                  </label>
                  <input
                    type="number"
                    className="form-control"
                    id="zip"
                    placeholder="79000"
                    required
                    name="zip"
                    onChange={onChangeHandler}
                    value={data.zip}
                  />
                </div>
              </div>

              <hr className="my-4" />

              <button
                className="w-100 btn btn-primary btn-lg"
                type="submit"
                disabled={cartItems.length === 0}
              >
                Continue to checkout
              </button>
            </form>
          </div>
        </div>
      </main>
    </div>
  );
};

export default PlaceOrder;
