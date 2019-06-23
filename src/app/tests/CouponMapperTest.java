//package app.tests;
//
//import app.dataTransferObjects.CouponDTO;
//import app.mappers.CouponMapper;
//import app.models.Coupon;
//import app.models.Shop;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//
//import static org.junit.Assert.*;
//import static org.junit.matchers.JUnitMatchers.*;
//
//
//import java.util.Date;
//
///**
// * @author dzimiks
// * Date: 23-06-2019 at 19:54
// */
//public class CouponMapperTest {
//
//	@Test
//	public void a() {
//
//	}
//
//	@Test
//	public void shouldDo() {
//		Shop shop = new Shop("shop-1", "Shop");
//		Coupon coupon = new Coupon(12345L, shop, "product", 12f, 17f, new Date(), new Date());
//		CouponDTO couponDTO = CouponMapper.INSTANCE.couponToCouponDTO(coupon);
//
//		assertThat(couponDTO).isNotNull();
//		assertThat(couponDTO.getProduct()).isEqualTo("product");
//		assertThat(couponDTO.getDiscountedPrice()).isEqualTo(12f);
//	}
//}
//
