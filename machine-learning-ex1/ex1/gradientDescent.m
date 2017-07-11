function [theta, J_history] = gradientDescent(X, y, theta, alpha, num_iters)
%GRADIENTDESCENT Performs gradient descent to learn theta
%   theta = GRADIENTDESCENT(X, y, theta, alpha, num_iters) updates theta by 
%   taking num_iters gradient steps with learning rate alpha

% Initialize some useful values
m = length(y); % number of training examples
J_history = zeros(num_iters, 1);



for iter = 1:num_iters
   H=X*theta;
   temp1=0;
   temp2=0;
   for i=1:m
    temp1=temp1+(H(i)-y(i))*X(i,1);
    temp2=temp2+(H(i)-y(i))*X(i,2); 
    
   end
  
   theta(1)=theta(1)-alpha/m*temp1;
   theta(2)=theta(2)-alpha/m*temp2;
  


    % ====================== YOUR CODE HERE ======================
    % Instructions: Perform a single gradient step on the parameter vector
    %               theta. 
    %
    % Hint: While debugging, it can be useful to print out the values
    %       of the cost function (computeCost) and gradient here.
    %







    % ============================================================

    % Save the cost J in every iteration    
    J_history(iter) = computeCost(X, y, theta);

end

end
