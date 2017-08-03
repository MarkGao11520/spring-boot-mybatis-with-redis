package com.gwf.family.business.product.service.impl;

import com.gwf.family.business.product.dao.ProductRepository;
import com.gwf.family.business.product.entity.Product;
import com.gwf.family.business.product.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import com.gwf.family.business.core.service.AbstractService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;



/**
 * Created by gwf on 2017-8-3 17:04:43.
 */
@Service
@Transactional
public class ProductServiceImpl extends AbstractService<Product> implements ProductService {
    @Autowired
    private ProductRepository productRepository;

}
