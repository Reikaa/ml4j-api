/*
 * Copyright 2017 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except
 * in compliance with the License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License
 * is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express
 * or implied. See the License for the specific language governing permissions and limitations under
 * the License.
 */

package org.ml4j.nn.layers;

import org.ml4j.nn.activationfunctions.DifferentiableActivationFunction;
import org.ml4j.nn.axons.Axons;
import org.ml4j.nn.neurons.NeuronsActivation;
import org.ml4j.nn.synapses.DirectedSynapses;

/**
 * Represents a Directed Layer of a NeuralNetwork - a Layer through which information propagates
 * from input neurons to output neurons in one direction.
 * 
 * @author Michael Lavelle
 * 

 * @param <L> The type of DirectedLayer
 */
public interface DirectedLayer<A extends Axons<?,?,?>, L extends DirectedLayer<A, L>>
    extends Layer<A, DirectedSynapses<?>, L> {

  /**
   * @return The number of input neurons (including any bias unit) to the left of this 
   *     Directed Layer.
   */
  int getInputNeuronCount();
  
  /**
   * @return The number of output neurons (including any bias unit) to the right of this 
   *     Directed Layer.
   */
  int getOutputNeuronCount();
  
  /**
   * @return The primary differentiable activation function configured for this DirectedLayer.   
   * 
   */
  DifferentiableActivationFunction getPrimaryActivationFunction();

  /**
   * Forward propagates the activation on the input neurons through this DirectedLayer, 
   * within this specified DirectedLayerContext.
   * 
   * @param inputNeuronsActivation The NeuronsActivation applied on the left hand side of this
   *        DirectedLayer as input
   * @param directedLayerContext The context under which we perform this forward propagation.
   * @return The DirectedLayerActivation output on the right hand side of this DirectedLayer, 
   *        encapsulating the artifacts generated by this propagation
   */
  DirectedLayerActivation forwardPropagate(NeuronsActivation inputNeuronsActivation, 
          DirectedLayerContext directedLayerContext);

  /**
   * Obtain the input NeuronsActivation which maximises the output
   *        of the specified output neuron.
   * 
   * @param outpuNeuronIndex The index of the output neuron we wish to obtain the
   *        output activation maximising input activation for.
   * @param directedLayerContext The directed layer context
   * @return The input NeuronsActivation which maximises the output
   *        of the specified output neuron.
   */
  NeuronsActivation getOptimalInputForOutputNeuron(int outpuNeuronIndex, 
      DirectedLayerContext directedLayerContext);
}