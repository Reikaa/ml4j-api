package org.ml4j.nn;

import org.ml4j.nn.layers.DirectedLayerContext;

/**
 * Encapsulates the runtime context used with a DirectedNeuralNetwork.
 * 
 * @author Michael Lavelle
 */
public interface DirectedNeuralNetworkContext extends NeuralNetworkContext {

  /**
   * Create the DirectedLayerContext for the DirectedLayer specified
   * by the layerIndex.
   * 
   * @param layerIndex The index of the DirectedLayer in this
   *        DirectedNeuralNetwork to create the context for.
   * @return The DirectedLayerContext for the DirectedLayer specified
   *        by the layerIndex.
   */
  DirectedLayerContext createLayerContext(int layerIndex);
}
