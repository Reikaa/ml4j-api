/*
 * Copyright 2017 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.ml4j.nn.axons;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.ml4j.Matrix;
import org.ml4j.MatrixFactory;
import org.ml4j.jblas.JBlasMatrixFactory;
import org.ml4j.nn.neurons.NeuronsActivation;
import org.ml4j.nn.neurons.NeuronsActivationContext;
import org.ml4j.nn.neurons.NeuronsActivationFeatureOrientation;

public class NeuronsActivationTest {

  private MatrixFactory matrixFactory;

  @Before
  public void setUp() {
    matrixFactory = new JBlasMatrixFactory();
  }

  @Test
  public void testConstructorNoBiasColumnsSpanFeatureSet() {

    Matrix matrix = matrixFactory.createRand(10, 784);
    boolean includesBias = false;

    NeuronsActivation neuronsActivation = new NeuronsActivation(matrix, includesBias,
        NeuronsActivationFeatureOrientation.COLUMNS_SPAN_FEATURE_SET);
    Matrix activations = neuronsActivation.getActivations();
    Assert.assertEquals(matrix.getRows(), activations.getRows());
    Assert.assertEquals(matrix.getColumns(), activations.getColumns());
    Assert.assertEquals(NeuronsActivationFeatureOrientation.COLUMNS_SPAN_FEATURE_SET,
        neuronsActivation.getFeatureOrientation());
    Assert.assertEquals(784, neuronsActivation.getFeatureCountExcludingBias());
    Assert.assertEquals(784, neuronsActivation.getFeatureCountIncludingBias());
    Assert.assertEquals(false, neuronsActivation.isBiasUnitIncluded());
  }

  @Test
  public void testConstructorNoBiasColumnsSpanFeatureSetWithBiasRequest() {

    Matrix matrix = matrixFactory.createRand(10, 784);
    boolean includesBias = false;

    NeuronsActivation neuronsActivation = new NeuronsActivation(matrix, includesBias,
        NeuronsActivationFeatureOrientation.COLUMNS_SPAN_FEATURE_SET);
    Matrix activations = neuronsActivation.getActivations();
    Assert.assertEquals(matrix.getRows(), activations.getRows());
    Assert.assertEquals(matrix.getColumns(), activations.getColumns());
    Assert.assertEquals(NeuronsActivationFeatureOrientation.COLUMNS_SPAN_FEATURE_SET,
        neuronsActivation.getFeatureOrientation());
    Assert.assertEquals(784, neuronsActivation.getFeatureCountExcludingBias());
    Assert.assertEquals(784, neuronsActivation.getFeatureCountIncludingBias());
    Assert.assertEquals(false, neuronsActivation.isBiasUnitIncluded());

    NeuronsActivationContext context = new NeuronsActivationContext() {

      /**
       * 
       */
      private static final long serialVersionUID = 1L;

      @Override
      public MatrixFactory getMatrixFactory() {
        return matrixFactory;
      }

    };
    NeuronsActivation neuronsActivationAfterBiasRequest =
        neuronsActivation.withBiasUnit(true, context);
    Matrix neuronsActivationAfterBiasRequestActivations =
        neuronsActivationAfterBiasRequest.getActivations();

    Assert.assertEquals(matrix.getRows(), neuronsActivationAfterBiasRequestActivations.getRows());
    Assert.assertEquals(matrix.getColumns() + 1,
        neuronsActivationAfterBiasRequestActivations.getColumns());
    Assert.assertEquals(NeuronsActivationFeatureOrientation.COLUMNS_SPAN_FEATURE_SET,
        neuronsActivation.getFeatureOrientation());
    Assert.assertEquals(784, neuronsActivationAfterBiasRequest.getFeatureCountExcludingBias());
    Assert.assertEquals(785, neuronsActivationAfterBiasRequest.getFeatureCountIncludingBias());
    Assert.assertEquals(true, neuronsActivationAfterBiasRequest.isBiasUnitIncluded());
    for (int r = 0; r < 10; r++) {
      Assert.assertEquals(1d, neuronsActivationAfterBiasRequestActivations.get(r, 0), 0.000000001);
    }
  }

  @Test
  public void testConstructorNoBiasColumnsSpanFeatureSetWithNoBiasRequest() {

    Matrix matrix = matrixFactory.createRand(10, 784);
    boolean includesBias = false;

    NeuronsActivation neuronsActivation = new NeuronsActivation(matrix, includesBias,
        NeuronsActivationFeatureOrientation.COLUMNS_SPAN_FEATURE_SET);
    Matrix activations = neuronsActivation.getActivations();
    Assert.assertEquals(matrix.getRows(), activations.getRows());
    Assert.assertEquals(matrix.getColumns(), activations.getColumns());
    Assert.assertEquals(NeuronsActivationFeatureOrientation.COLUMNS_SPAN_FEATURE_SET,
        neuronsActivation.getFeatureOrientation());
    Assert.assertEquals(784, neuronsActivation.getFeatureCountExcludingBias());
    Assert.assertEquals(784, neuronsActivation.getFeatureCountIncludingBias());
    Assert.assertEquals(false, neuronsActivation.isBiasUnitIncluded());

    NeuronsActivationContext context = new NeuronsActivationContext() {

      /**
       * 
       */
      private static final long serialVersionUID = 1L;

      @Override
      public MatrixFactory getMatrixFactory() {
        return matrixFactory;
      }

    };
    NeuronsActivation neuronsActivationAfterBiasRequest =
        neuronsActivation.withBiasUnit(false, context);
    Matrix neuronsActivationAfterBiasRequestActivations =
        neuronsActivationAfterBiasRequest.getActivations();

    Assert.assertEquals(matrix.getRows(), neuronsActivationAfterBiasRequestActivations.getRows());
    Assert.assertEquals(matrix.getColumns(),
        neuronsActivationAfterBiasRequestActivations.getColumns());
    Assert.assertEquals(NeuronsActivationFeatureOrientation.COLUMNS_SPAN_FEATURE_SET,
        neuronsActivation.getFeatureOrientation());
    Assert.assertEquals(784, neuronsActivationAfterBiasRequest.getFeatureCountExcludingBias());
    Assert.assertEquals(784, neuronsActivationAfterBiasRequest.getFeatureCountIncludingBias());
    Assert.assertEquals(false, neuronsActivationAfterBiasRequest.isBiasUnitIncluded());

    for (int r = 0; r < 10; r++) {
      Assert.assertEquals(matrix.get(r, 0), neuronsActivationAfterBiasRequestActivations.get(r, 0),
          0.000000001);
    }
  }


  @Test
  public void testConstructorNoBiasRowsSpanFeatureSetWithBiasRequest() {

    Matrix matrix = matrixFactory.createRand(784, 10);
    boolean includesBias = false;

    NeuronsActivation neuronsActivation = new NeuronsActivation(matrix, includesBias,
        NeuronsActivationFeatureOrientation.ROWS_SPAN_FEATURE_SET);
    Matrix activations = neuronsActivation.getActivations();
    Assert.assertEquals(matrix.getRows(), activations.getRows());
    Assert.assertEquals(matrix.getColumns(), activations.getColumns());
    Assert.assertEquals(NeuronsActivationFeatureOrientation.ROWS_SPAN_FEATURE_SET,
        neuronsActivation.getFeatureOrientation());
    Assert.assertEquals(784, neuronsActivation.getFeatureCountExcludingBias());
    Assert.assertEquals(784, neuronsActivation.getFeatureCountIncludingBias());
    Assert.assertEquals(false, neuronsActivation.isBiasUnitIncluded());

    NeuronsActivationContext context = new NeuronsActivationContext() {

      /**
       * 
       */
      private static final long serialVersionUID = 1L;

      @Override
      public MatrixFactory getMatrixFactory() {
        return matrixFactory;
      }

    };
    NeuronsActivation neuronsActivationAfterBiasRequest =
        neuronsActivation.withBiasUnit(true, context);
    Matrix neuronsActivationAfterBiasRequestActivations =
        neuronsActivationAfterBiasRequest.getActivations();

    Assert.assertEquals(matrix.getRows() + 1,
        neuronsActivationAfterBiasRequestActivations.getRows());
    Assert.assertEquals(matrix.getColumns(),
        neuronsActivationAfterBiasRequestActivations.getColumns());
    Assert.assertEquals(NeuronsActivationFeatureOrientation.ROWS_SPAN_FEATURE_SET,
        neuronsActivation.getFeatureOrientation());
    Assert.assertEquals(784, neuronsActivationAfterBiasRequest.getFeatureCountExcludingBias());
    Assert.assertEquals(785, neuronsActivationAfterBiasRequest.getFeatureCountIncludingBias());
    Assert.assertEquals(true, neuronsActivationAfterBiasRequest.isBiasUnitIncluded());
    for (int c = 0; c < 10; c++) {
      Assert.assertEquals(1d, neuronsActivationAfterBiasRequestActivations.get(0, c), 0.000000001);
    }
  }

  @Test
  public void testConstructorNoBiasRowsSpanFeatureSetWithNoBiasRequest() {

    Matrix matrix = matrixFactory.createRand(784, 10);
    boolean includesBias = false;

    NeuronsActivation neuronsActivation = new NeuronsActivation(matrix, includesBias,
        NeuronsActivationFeatureOrientation.ROWS_SPAN_FEATURE_SET);
    Matrix activations = neuronsActivation.getActivations();
    Assert.assertEquals(matrix.getRows(), activations.getRows());
    Assert.assertEquals(matrix.getColumns(), activations.getColumns());
    Assert.assertEquals(NeuronsActivationFeatureOrientation.ROWS_SPAN_FEATURE_SET,
        neuronsActivation.getFeatureOrientation());
    Assert.assertEquals(784, neuronsActivation.getFeatureCountExcludingBias());
    Assert.assertEquals(784, neuronsActivation.getFeatureCountIncludingBias());
    Assert.assertEquals(false, neuronsActivation.isBiasUnitIncluded());

    NeuronsActivationContext context = new NeuronsActivationContext() {

      /**
       * 
       */
      private static final long serialVersionUID = 1L;

      @Override
      public MatrixFactory getMatrixFactory() {
        return matrixFactory;
      }

    };
    NeuronsActivation neuronsActivationAfterBiasRequest =
        neuronsActivation.withBiasUnit(false, context);
    Matrix neuronsActivationAfterBiasRequestActivations =
        neuronsActivationAfterBiasRequest.getActivations();

    Assert.assertEquals(matrix.getRows(), neuronsActivationAfterBiasRequestActivations.getRows());
    Assert.assertEquals(matrix.getColumns(),
        neuronsActivationAfterBiasRequestActivations.getColumns());
    Assert.assertEquals(NeuronsActivationFeatureOrientation.ROWS_SPAN_FEATURE_SET,
        neuronsActivation.getFeatureOrientation());
    Assert.assertEquals(784, neuronsActivationAfterBiasRequest.getFeatureCountExcludingBias());
    Assert.assertEquals(784, neuronsActivationAfterBiasRequest.getFeatureCountIncludingBias());
    Assert.assertEquals(false, neuronsActivationAfterBiasRequest.isBiasUnitIncluded());

    for (int c = 0; c < 10; c++) {
      Assert.assertEquals(matrix.get(0, c), neuronsActivationAfterBiasRequestActivations.get(0, c),
          0.000000001);
    }
  }

  @Test(expected = IllegalArgumentException.class)
  public void testConstructorWithIncorrectBiasColumnsSpanFeatureSet() {

    Matrix matrix = matrixFactory.createRand(10, 785);
    boolean includesBias = true;

    new NeuronsActivation(matrix, includesBias,
        NeuronsActivationFeatureOrientation.COLUMNS_SPAN_FEATURE_SET);
  }



  @Test
  public void testConstructorWithCorrectBiasColumnsSpanFeatureSet() {

    Matrix matrix = matrixFactory.createRand(10, 785);
    boolean includesBias = true;
    for (int r = 0; r < 10; r++) {
      matrix.put(r, 0, 1d);
    }

    NeuronsActivation neuronsActivation = new NeuronsActivation(matrix, includesBias,
        NeuronsActivationFeatureOrientation.COLUMNS_SPAN_FEATURE_SET);
    Matrix activations = neuronsActivation.getActivations();
    Assert.assertEquals(matrix.getRows(), activations.getRows());
    Assert.assertEquals(matrix.getColumns(), activations.getColumns());
    Assert.assertEquals(NeuronsActivationFeatureOrientation.COLUMNS_SPAN_FEATURE_SET,
        neuronsActivation.getFeatureOrientation());
    Assert.assertEquals(784, neuronsActivation.getFeatureCountExcludingBias());
    Assert.assertEquals(785, neuronsActivation.getFeatureCountIncludingBias());
    Assert.assertEquals(true, neuronsActivation.isBiasUnitIncluded());

    for (int r = 0; r < 10; r++) {
      Assert.assertEquals(1d, activations.get(r, 0), 0.000000001);
    }

  }

  @Test
  public void testConstructorWithCorrectBiasColumnsSpanFeatureSetWithBiasRequest() {

    Matrix matrix = matrixFactory.createRand(10, 785);
    boolean includesBias = true;
    for (int r = 0; r < 10; r++) {
      matrix.put(r, 0, 1d);
    }

    NeuronsActivation neuronsActivation = new NeuronsActivation(matrix, includesBias,
        NeuronsActivationFeatureOrientation.COLUMNS_SPAN_FEATURE_SET);
    Matrix activations = neuronsActivation.getActivations();
    Assert.assertEquals(matrix.getRows(), activations.getRows());
    Assert.assertEquals(matrix.getColumns(), activations.getColumns());
    Assert.assertEquals(NeuronsActivationFeatureOrientation.COLUMNS_SPAN_FEATURE_SET,
        neuronsActivation.getFeatureOrientation());
    Assert.assertEquals(784, neuronsActivation.getFeatureCountExcludingBias());
    Assert.assertEquals(785, neuronsActivation.getFeatureCountIncludingBias());
    Assert.assertEquals(true, neuronsActivation.isBiasUnitIncluded());

    for (int r = 0; r < 10; r++) {
      Assert.assertEquals(1d, activations.get(r, 0), 0.000000001);
    }

    NeuronsActivationContext context = new NeuronsActivationContext() {

      /**
       * 
       */
      private static final long serialVersionUID = 1L;

      @Override
      public MatrixFactory getMatrixFactory() {
        return matrixFactory;
      }

    };
    NeuronsActivation neuronsActivationAfterBiasRequest =
        neuronsActivation.withBiasUnit(true, context);
    Matrix neuronsActivationAfterBiasRequestActivations =
        neuronsActivationAfterBiasRequest.getActivations();

    Assert.assertEquals(matrix.getRows(), neuronsActivationAfterBiasRequestActivations.getRows());
    Assert.assertEquals(matrix.getColumns(),
        neuronsActivationAfterBiasRequestActivations.getColumns());
    Assert.assertEquals(NeuronsActivationFeatureOrientation.COLUMNS_SPAN_FEATURE_SET,
        neuronsActivation.getFeatureOrientation());
    Assert.assertEquals(784, neuronsActivationAfterBiasRequest.getFeatureCountExcludingBias());
    Assert.assertEquals(785, neuronsActivationAfterBiasRequest.getFeatureCountIncludingBias());
    Assert.assertEquals(true, neuronsActivationAfterBiasRequest.isBiasUnitIncluded());

    for (int r = 0; r < 10; r++) {
      Assert.assertEquals(1d, neuronsActivationAfterBiasRequestActivations.get(r, 0), 0.000000001);
    }

  }

  @Test
  public void testConstructorWithCorrectBiasColumnsSpanFeatureSetWithNoBiasRequest() {

    Matrix matrix = matrixFactory.createRand(10, 785);
    boolean includesBias = true;
    for (int r = 0; r < 10; r++) {
      matrix.put(r, 0, 1d);
    }

    NeuronsActivation neuronsActivation = new NeuronsActivation(matrix, includesBias,
        NeuronsActivationFeatureOrientation.COLUMNS_SPAN_FEATURE_SET);
    Matrix activations = neuronsActivation.getActivations();
    Assert.assertEquals(matrix.getRows(), activations.getRows());
    Assert.assertEquals(matrix.getColumns(), activations.getColumns());
    Assert.assertEquals(NeuronsActivationFeatureOrientation.COLUMNS_SPAN_FEATURE_SET,
        neuronsActivation.getFeatureOrientation());
    Assert.assertEquals(784, neuronsActivation.getFeatureCountExcludingBias());
    Assert.assertEquals(785, neuronsActivation.getFeatureCountIncludingBias());
    Assert.assertEquals(true, neuronsActivation.isBiasUnitIncluded());

    for (int r = 0; r < 10; r++) {
      Assert.assertEquals(1d, activations.get(r, 0), 0.000000001);
    }

    NeuronsActivationContext context = new NeuronsActivationContext() {

      /**
       * 
       */
      private static final long serialVersionUID = 1L;

      @Override
      public MatrixFactory getMatrixFactory() {
        return matrixFactory;
      }

    };
    NeuronsActivation neuronsActivationAfterBiasRequest =
        neuronsActivation.withBiasUnit(false, context);
    Matrix neuronsActivationAfterBiasRequestActivations =
        neuronsActivationAfterBiasRequest.getActivations();

    Assert.assertEquals(matrix.getRows(), neuronsActivationAfterBiasRequestActivations.getRows());
    Assert.assertEquals(matrix.getColumns() - 1,
        neuronsActivationAfterBiasRequestActivations.getColumns());
    Assert.assertEquals(NeuronsActivationFeatureOrientation.COLUMNS_SPAN_FEATURE_SET,
        neuronsActivation.getFeatureOrientation());
    Assert.assertEquals(784, neuronsActivationAfterBiasRequest.getFeatureCountExcludingBias());
    Assert.assertEquals(784, neuronsActivationAfterBiasRequest.getFeatureCountIncludingBias());
    Assert.assertEquals(false, neuronsActivationAfterBiasRequest.isBiasUnitIncluded());

    for (int r = 0; r < 10; r++) {
      Assert.assertEquals(matrix.get(r, 1), neuronsActivationAfterBiasRequestActivations.get(r, 0),
          0.000000001);
    }

  }

  @Test
  public void testConstructorNoBiasRowsSpanFeatureSet() {

    Matrix matrix = matrixFactory.createRand(784, 10);
    boolean includesBias = false;

    NeuronsActivation neuronsActivation = new NeuronsActivation(matrix, includesBias,
        NeuronsActivationFeatureOrientation.ROWS_SPAN_FEATURE_SET);
    Matrix activations = neuronsActivation.getActivations();
    Assert.assertEquals(matrix.getRows(), activations.getRows());
    Assert.assertEquals(matrix.getColumns(), activations.getColumns());
    Assert.assertEquals(NeuronsActivationFeatureOrientation.ROWS_SPAN_FEATURE_SET,
        neuronsActivation.getFeatureOrientation());
    Assert.assertEquals(784, neuronsActivation.getFeatureCountExcludingBias());
    Assert.assertEquals(784, neuronsActivation.getFeatureCountIncludingBias());
    Assert.assertEquals(false, neuronsActivation.isBiasUnitIncluded());

  }


  @Test
  public void testConstructorWithCorrectBiasRowsSpanFeatureSet() {

    Matrix matrix = matrixFactory.createRand(785, 10);

    boolean includesBias = true;
    for (int c = 0; c < 10; c++) {
      matrix.put(0, c, 1d);
    }
    NeuronsActivation neuronsActivation = new NeuronsActivation(matrix, includesBias,
        NeuronsActivationFeatureOrientation.ROWS_SPAN_FEATURE_SET);
    Matrix activations = neuronsActivation.getActivations();
    Assert.assertEquals(matrix.getRows(), activations.getRows());
    Assert.assertEquals(matrix.getColumns(), activations.getColumns());
    Assert.assertEquals(NeuronsActivationFeatureOrientation.ROWS_SPAN_FEATURE_SET,
        neuronsActivation.getFeatureOrientation());
    Assert.assertEquals(784, neuronsActivation.getFeatureCountExcludingBias());
    Assert.assertEquals(785, neuronsActivation.getFeatureCountIncludingBias());
    Assert.assertEquals(true, neuronsActivation.isBiasUnitIncluded());

    for (int c = 0; c < 10; c++) {
      Assert.assertEquals(1d, activations.get(0, c), 0.000000001);
    }
  }

  @Test
  public void testConstructorWithCorrectBiasRowsSpanFeatureSetWithBiasRequest() {

    Matrix matrix = matrixFactory.createRand(785, 10);

    boolean includesBias = true;
    for (int c = 0; c < 10; c++) {
      matrix.put(0, c, 1d);
    }
    NeuronsActivation neuronsActivation = new NeuronsActivation(matrix, includesBias,
        NeuronsActivationFeatureOrientation.ROWS_SPAN_FEATURE_SET);
    Matrix activations = neuronsActivation.getActivations();
    Assert.assertEquals(matrix.getRows(), activations.getRows());
    Assert.assertEquals(matrix.getColumns(), activations.getColumns());
    Assert.assertEquals(NeuronsActivationFeatureOrientation.ROWS_SPAN_FEATURE_SET,
        neuronsActivation.getFeatureOrientation());
    Assert.assertEquals(784, neuronsActivation.getFeatureCountExcludingBias());
    Assert.assertEquals(785, neuronsActivation.getFeatureCountIncludingBias());
    Assert.assertEquals(true, neuronsActivation.isBiasUnitIncluded());

    for (int c = 0; c < 10; c++) {
      Assert.assertEquals(1d, activations.get(0, c), 0.000000001);
    }

    NeuronsActivationContext context = new NeuronsActivationContext() {

      /**
       * 
       */
      private static final long serialVersionUID = 1L;

      @Override
      public MatrixFactory getMatrixFactory() {
        return matrixFactory;
      }

    };
    NeuronsActivation neuronsActivationAfterBiasRequest =
        neuronsActivation.withBiasUnit(true, context);
    Matrix neuronsActivationAfterBiasRequestActivations =
        neuronsActivationAfterBiasRequest.getActivations();

    Assert.assertEquals(matrix.getRows(), neuronsActivationAfterBiasRequestActivations.getRows());
    Assert.assertEquals(matrix.getColumns(),
        neuronsActivationAfterBiasRequestActivations.getColumns());
    Assert.assertEquals(NeuronsActivationFeatureOrientation.ROWS_SPAN_FEATURE_SET,
        neuronsActivation.getFeatureOrientation());
    Assert.assertEquals(784, neuronsActivationAfterBiasRequest.getFeatureCountExcludingBias());
    Assert.assertEquals(785, neuronsActivationAfterBiasRequest.getFeatureCountIncludingBias());
    Assert.assertEquals(true, neuronsActivationAfterBiasRequest.isBiasUnitIncluded());

    for (int c = 0; c < 10; c++) {
      Assert.assertEquals(1d, neuronsActivationAfterBiasRequestActivations.get(0, c), 0.000000001);
    }

  }

  @Test
  public void testConstructorWithCorrectBiasRowsSpanFeatureSetWithNoBiasRequest() {

    Matrix matrix = matrixFactory.createRand(785, 10);

    boolean includesBias = true;
    for (int c = 0; c < 10; c++) {
      matrix.put(0, c, 1d);
    }
    NeuronsActivation neuronsActivation = new NeuronsActivation(matrix, includesBias,
        NeuronsActivationFeatureOrientation.ROWS_SPAN_FEATURE_SET);
    Matrix activations = neuronsActivation.getActivations();
    Assert.assertEquals(matrix.getRows(), activations.getRows());
    Assert.assertEquals(matrix.getColumns(), activations.getColumns());
    Assert.assertEquals(NeuronsActivationFeatureOrientation.ROWS_SPAN_FEATURE_SET,
        neuronsActivation.getFeatureOrientation());
    Assert.assertEquals(784, neuronsActivation.getFeatureCountExcludingBias());
    Assert.assertEquals(785, neuronsActivation.getFeatureCountIncludingBias());
    Assert.assertEquals(true, neuronsActivation.isBiasUnitIncluded());

    for (int c = 0; c < 10; c++) {
      Assert.assertEquals(1d, activations.get(0, c), 0.000000001);
    }

    NeuronsActivationContext context = new NeuronsActivationContext() {

      /**
       * 
       */
      private static final long serialVersionUID = 1L;

      @Override
      public MatrixFactory getMatrixFactory() {
        return matrixFactory;
      }

    };
    NeuronsActivation neuronsActivationAfterBiasRequest =
        neuronsActivation.withBiasUnit(false, context);
    Matrix neuronsActivationAfterBiasRequestActivations =
        neuronsActivationAfterBiasRequest.getActivations();

    Assert.assertEquals(matrix.getRows() - 1,
        neuronsActivationAfterBiasRequestActivations.getRows());
    Assert.assertEquals(matrix.getColumns(),
        neuronsActivationAfterBiasRequestActivations.getColumns());
    Assert.assertEquals(NeuronsActivationFeatureOrientation.ROWS_SPAN_FEATURE_SET,
        neuronsActivation.getFeatureOrientation());
    Assert.assertEquals(784, neuronsActivationAfterBiasRequest.getFeatureCountExcludingBias());
    Assert.assertEquals(784, neuronsActivationAfterBiasRequest.getFeatureCountIncludingBias());
    Assert.assertEquals(false, neuronsActivationAfterBiasRequest.isBiasUnitIncluded());

    for (int c = 0; c < 10; c++) {
      Assert.assertEquals(matrix.get(1, c), neuronsActivationAfterBiasRequestActivations.get(0, c),
          0.000000001);
    }

  }


  @Test(expected = IllegalArgumentException.class)
  public void testConstructorWithIncorrectBiasRowsSpanFeatureSet() {

    Matrix matrix = matrixFactory.createRand(785, 10);

    boolean includesBias = true;

    new NeuronsActivation(matrix, includesBias,
        NeuronsActivationFeatureOrientation.ROWS_SPAN_FEATURE_SET);
  }

}