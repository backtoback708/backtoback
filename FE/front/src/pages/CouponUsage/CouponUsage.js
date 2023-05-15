import Qs from "query-string";
import React, { useEffect } from "react";
import { Link, useLocation } from "react-router-dom";
import { CTLoading, useLoading, useCoupon } from "../../components";
import CouponUsageView from "./CouponUsageView";

const CouponUsage = () => {
  const { couponList, couponGetAll } = useCoupon();
  const { loading, setLoading } = useLoading(true);
  const location = useLocation();

  const query = Qs.parse(location.search);

  console.log(query);
  console.log(Qs.stringify(query));

  const fetch = async () => {
    try {
      await couponGetAll(0);
    } catch (e) {
      console.log(e);
    } finally { 
      await setLoading(false);
    }
  };

  useEffect(() => {
    fetch();
  }, []);

  return (

    loading ? (
      <CTLoading />
    ) : (
      <CouponUsageView
        total={couponList.total}
        results={couponList.results}
      />
    )
  );
};

export default CouponUsage;
