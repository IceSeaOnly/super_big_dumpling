package site.binghai.SuperBigDumpling.web.entity.converters;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import site.binghai.SuperBigDumpling.common.entity.things.Property;

/**
 * Created by IceSea on 2017/10/22.
 * GitHub: https://github.com/IceSeaOnly
 */
@Component
public class String2TradeItemProperty implements Converter<String, Property> {
    @Override
    public Property convert(String s) {
        Property p = new Property();
        p.setId(Integer.parseInt(s));
        return p;
    }
}
